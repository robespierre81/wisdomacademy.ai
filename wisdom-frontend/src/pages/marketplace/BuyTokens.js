import React, { useState } from "react";
import { ethers } from "ethers";
import "../../styles/BuyTokens.css";

const BuyTokens = () => {
    const [amount, setAmount] = useState("");
    const [message, setMessage] = useState("");

    const handleBuyTokens = async () => {
        if (!window.ethereum) {
            setMessage("MetaMask is required to buy tokens.");
            return;
        }

        try {
            const provider = new ethers.BrowserProvider(window.ethereum);
            const signer = await provider.getSigner();
            const contractAddress = "0xYourSmartContractAddress"; // Replace with real address
            const contractABI = []; // Replace with actual ABI

            const contract = new ethers.Contract(contractAddress, contractABI, signer);
            const tx = await contract.buyTokens({ value: ethers.parseEther(amount) });

            setMessage("Transaction sent! Waiting for confirmation...");
            await tx.wait();
            setMessage(`Successfully purchased ${amount} WSD tokens!`);
        } catch (error) {
            console.error("Error buying tokens:", error);
            setMessage("Transaction failed.");
        }
    };

    return (
        <div className="buy-tokens">
            <h1>Buy Tokens</h1>
            <p>Enter the amount of WSD tokens you want to buy.</p>

            <input 
                type="number"
                placeholder="Enter amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
            />
            <button onClick={handleBuyTokens}>Buy Tokens</button>

            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default BuyTokens;