import Web3 from "web3";

// Blockchain Connection
const web3 = new Web3("http://74.208.195.193:8545"); // Replace with your blockchain RPC

// Smart Contract Address & ABI
const contractAddress = "0x5ae47b3d0206fa42386189cf33bc21E3938A0386"; // Paste deployed contract address
const contractABI = [
    {
        "inputs": [{"internalType": "string", "name": "_data", "type": "string"}],
        "name": "storeUserData",
        "outputs": [],
        "stateMutability": "nonpayable",
        "type": "function"
    },
    {
        "inputs": [{"internalType": "address", "name": "user", "type": "address"}],
        "name": "retrieveUserData",
        "outputs": [{"internalType": "string", "name": "", "type": "string"}],
        "stateMutability": "view",
        "type": "function"
    }
];

// Connect Contract
const contract = new web3.eth.Contract(contractABI, contractAddress);

// **Function to Store Data**
export const storeData = async (data) => {
    const accounts = await window.ethereum.request({ method: "eth_requestAccounts" });
    const userAccount = accounts[0];

    await contract.methods.storeUserData(data).send({ from: userAccount });
    console.log("Data saved successfully!");
};

// **Function to Retrieve Data**
export const retrieveData = async (userAddress) => {
    const result = await contract.methods.retrieveUserData(userAddress).call();
    console.log("Stored Data:", result);
    return result;
};
