import { ethers } from "ethers";

const wisdomProvider = new ethers.JsonRpcProvider("http://74.208.195.193:8545");
const contractAddress = "0xYourContractAddress"; // Replace with actual contract address
const contractABI = [
    {
        "constant": true,
        "inputs": [{ "name": "user", "type": "address" }],
        "name": "getData",
        "outputs": [{ "name": "", "type": "string" }],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    },
    {
        "constant": false,
        "inputs": [{ "name": "data", "type": "string" }],
        "name": "storeData",
        "outputs": [],
        "payable": false,
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "constant": true,
        "inputs": [{ "name": "user", "type": "address" }],
        "name": "getUserBalance",
        "outputs": [{ "name": "", "type": "uint256" }],
        "payable": false,
        "stateMutability": "view",
        "type": "function"
    }
];

// Function to store data on the blockchain
export const storeData = async (inputData) => {
    try {
        if (!window.ethereum) throw new Error("MetaMask not detected");

        const provider = new ethers.BrowserProvider(window.ethereum);
        const signer = await provider.getSigner();
        const contract = new ethers.Contract(contractAddress, contractABI, signer);

        const tx = await contract.storeData(inputData);
        await tx.wait();
        console.log("Data stored successfully:", tx.hash);
        return tx.hash;
    } catch (error) {
        console.error("Error storing data:", error);
        throw error;
    }
};

// Function to retrieve data from the blockchain
export const retrieveData = async (walletAddress) => {
    try {
        const contract = new ethers.Contract(contractAddress, contractABI, wisdomProvider);
        const data = await contract.getData(walletAddress);
        console.log("Retrieved data:", data);
        return data;
    } catch (error) {
        console.error("Error retrieving data:", error);
        throw error;
    }
};

// Function to get user balance from the blockchain
export const getUserBalance = async (walletAddress) => {
    try {
        const contract = new ethers.Contract(contractAddress, contractABI, wisdomProvider);
        const balance = await contract.getUserBalance(walletAddress);
        console.log("User Balance:", balance.toString());
        return balance.toString();
    } catch (error) {
        console.error("Error fetching balance:", error);
        throw error;
    }
};
