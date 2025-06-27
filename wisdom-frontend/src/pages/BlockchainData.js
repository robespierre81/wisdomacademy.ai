import React, { useState, useEffect } from "react";
import { storeData, retrieveData } from "../components/service";
import { ethers } from "ethers";

const BlockchainData = () => {
    const [walletAddress, setWalletAddress] = useState("");
    const [inputData, setInputData] = useState("");
    const [retrievedData, setRetrievedData] = useState("");

    const wisdomProvider = new ethers.JsonRpcProvider("http://192.168.1.100:8545");
    const contractAddress = "0xYourContractAddress"; // Replace with actual contract address
    const contractABI = [ /* ABI JSON here */ ];

    // Function to connect MetaMask and get the wallet address
    const connectWallet = async () => {
        if (window.ethereum) {
            try {
                const provider = new ethers.BrowserProvider(window.ethereum);
                const accounts = await provider.send("eth_requestAccounts", []);
                setWalletAddress(accounts[0]);
            } catch (error) {
                console.error("Error connecting wallet:", error);
            }
        } else {
            alert("MetaMask not detected. Please install MetaMask.");
        }
    };

    // Function to store data on blockchain
    const handleSave = async () => {
        if (!walletAddress) {
            alert("Please connect your wallet first.");
            return;
        }
        await storeData(inputData);
        alert("Data stored successfully!");
    };

    // Function to retrieve stored blockchain data
    const handleRetrieve = async () => {
        if (!walletAddress) {
            alert("Please connect your wallet first.");
            return;
        }
        const data = await retrieveData(walletAddress);
        setRetrievedData(data);
    };

    // Function to get user balance from the Wisdom Blockchain
    async function getUserBalance() {
        if (!walletAddress) {
            alert("Please connect your wallet first.");
            return;
        }
        const contract = new ethers.Contract(contractAddress, contractABI, wisdomProvider);
        const balance = await contract.getUserBalance(walletAddress);
        console.log("User Balance:", balance.toString());
    }

    return (
        <div className="flex flex-col items-center justify-center h-screen bg-gray-100">
            <div className="bg-white p-6 rounded-lg shadow-lg text-center w-96">
                <h2 className="text-2xl font-bold mb-4">Save & Retrieve Data from Blockchain</h2>
                {walletAddress ? (
                    <p className="text-sm text-gray-600 break-all">Connected Wallet: {walletAddress}</p>
                ) : (
                    <button
                        onClick={connectWallet}
                        className="bg-blue-500 text-white px-4 py-2 rounded-lg mt-4 hover:bg-blue-600"
                    >
                        Connect Wallet
                    </button>
                )}
                <input
                    type="text"
                    value={inputData}
                    onChange={(e) => setInputData(e.target.value)}
                    className="border p-2 mt-4 w-full"
                    placeholder="Enter data to store"
                />
                <button
                    onClick={handleSave}
                    className="bg-green-500 text-white px-4 py-2 rounded-lg mt-4 hover:bg-green-600"
                >
                    Save Data
                </button>
                <button
                    onClick={handleRetrieve}
                    className="bg-purple-500 text-white px-4 py-2 rounded-lg mt-4 hover:bg-purple-600"
                >
                    Retrieve Data
                </button>
                <p className="text-lg mt-4 font-semibold">Stored Data: {retrievedData}</p>
                <button
                    onClick={getUserBalance}
                    className="bg-yellow-500 text-white px-4 py-2 rounded-lg mt-4 hover:bg-yellow-600"
                >
                    Get Balance
                </button>
            </div>
        </div>
    );
};

export default BlockchainData;
