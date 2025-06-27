import React, { useState, useEffect } from "react";
import { ethers } from "ethers";
import "../../styles/MyTransactions.css";

const MyTransactions = () => {
    const [transactions, setTransactions] = useState([]);
    const walletAddress = "0xYourWalletAddress"; // Replace with real wallet address

    useEffect(() => {
        const fetchTransactions = async () => {
            try {
                const provider = new ethers.JsonRpcProvider("http://74.208.195.193:8545"); // Adjust if needed
                const history = await provider.getHistory(walletAddress);
                setTransactions(history);
            } catch (error) {
                console.error("Error fetching transactions:", error);
            }
        };

        fetchTransactions();
    }, []);

    return (
        <div className="my-transactions">
            <h1>My Transactions</h1>
            <p>View your past token purchases and rewards.</p>

            <ul>
                {transactions.map((tx, index) => (
                    <li key={index}>
                        <p>Hash: {tx.hash}</p>
                        <p>From: {tx.from}</p>
                        <p>To: {tx.to}</p>
                        <p>Value: {ethers.formatEther(tx.value)} WSD</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MyTransactions;