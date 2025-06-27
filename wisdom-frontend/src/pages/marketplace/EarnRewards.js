import React, { useState } from "react";
import { ethers } from "ethers";
import "../../styles/EarnRewards.css";

const EarnRewards = () => {
    const [rewards, setRewards] = useState(["Completed Course Reward: 10 WSD", "Mentor Bonus: 5 WSD"]);
    const [message, setMessage] = useState("");

    const handleClaimRewards = async () => {
        if (!window.ethereum) {
            setMessage("MetaMask is required to claim rewards.");
            return;
        }

        try {
            const provider = new ethers.BrowserProvider(window.ethereum);
            const signer = await provider.getSigner();
            const contractAddress = "0xYourSmartContractAddress"; // Replace with real address
            const contractABI = []; // Replace with actual ABI

            const contract = new ethers.Contract(contractAddress, contractABI, signer);
            const tx = await contract.claimRewards(); // Adjust function name based on smart contract

            setMessage("Claiming rewards...");
            await tx.wait();
            setMessage("Rewards claimed successfully!");
        } catch (error) {
            console.error("Error claiming rewards:", error);
            setMessage("Failed to claim rewards.");
        }
    };

    return (
        <div className="earn-rewards">
            <h1>Earn Rewards</h1>
            <p>Complete courses and mentoring to earn WSD tokens.</p>

            <ul>
                {rewards.map((reward, index) => (
                    <li key={index}>{reward}</li>
                ))}
            </ul>

            <button onClick={handleClaimRewards}>Claim Rewards</button>
            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default EarnRewards;