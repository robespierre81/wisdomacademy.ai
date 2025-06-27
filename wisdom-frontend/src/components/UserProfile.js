import React, { useState, useEffect } from "react";
import { ethers } from "ethers";

import UserCertificates from "../pages/learn/UserCertificates";
import "../styles/UserProfile.css"; // Ensure you create this CSS file

const wisdomProvider = new ethers.JsonRpcProvider("https://www.wisdomacademy.ai/blockchain/");

const UserProfile = () => {
    const [walletAddress, setWalletAddress] = useState("");
    const [balance, setBalance] = useState(null);
    const [connected, setConnected] = useState(false);
    const [profile, setProfile] = useState({
        name: "Guest User",
        bio: "No bio available.",
        profilePic: "/pictures/default-avatar.png",
        badges: ["New Learner", "Java Hero"],
        learningPlans: []
    });

    // Connect MetaMask
    const connectWallet = async () => {
        if (window.ethereum) {
            try {
                const provider = new ethers.BrowserProvider(window.ethereum);
                const accounts = await provider.send("eth_requestAccounts", []);
                setWalletAddress(accounts[0]);
                setConnected(true);
                fetchBalance(accounts[0]);
            } catch (error) {
                console.error("Wallet connection failed:", error);
            }
        } else {
            alert("MetaMask not detected. Please install MetaMask.");
        }
    };

    // Fetch balance from Wisdom Blockchain
    const fetchBalance = async (address) => {
        try {
            const balanceWei = await wisdomProvider.getBalance(address);
            const balanceEth = ethers.formatEther(balanceWei);
            setBalance(balanceEth);
        } catch (error) {
            console.error("Failed to fetch balance:", error);
        }
    };

    return (
        <div className="user-profile">
            <div className="profile-header">
                <img src={profile.profilePic} alt="Profile" className="profile-pic" />
                <h2>{profile.name}</h2>
                {connected ? (
                    <>
                        <p className="wallet-address">{walletAddress}</p>
                        <p><strong>WSD Balance:</strong> {balance} WSD</p>
                    </>
                ) : (
                    <button onClick={connectWallet} className="connect-btn">
                        Connect Wallet
                    </button>
                )}
            </div>
            <UserCertificates walletAddress={localStorage.getItem("user")} />
            
            <div className="badges-section">
                <h3>Achievements</h3>
                <div className="badges-container">
                    {profile.badges.map((badge, index) => (
                        <span key={index} className="badge">{badge}</span>
                    ))}
                </div>
            </div>

            <div className="badges-section">
                <h3>Learning Plans</h3>
                <div className="badges-container">
                    {profile.learningPlans.map((plan, index) => (
                        <span key={index} className="badge">{plan}</span>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default UserProfile;
