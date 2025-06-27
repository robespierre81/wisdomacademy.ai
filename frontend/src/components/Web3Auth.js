import React, { useState, useEffect } from 'react';
import Web3 from 'web3';

const Web3Auth = ({ onUserId }) => {
    useEffect(() => {
        const initWeb3 = async () => {
            if (window.ethereum) {
                const web3 = new Web3(window.ethereum);
                try {
                    // Request account access if needed
                    const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });
                    const userId = accounts[0]; // Use the first account as the user ID
                    onUserId(userId); // Pass the userId to the parent component
                } catch (error) {
                    console.error("User denied Web3 access or error occurred", error);
                }
            } else {
                console.error("Web3 provider not detected. Please install MetaMask.");
            }
        };

        initWeb3();
    }, [onUserId]);

    return null; // This component doesn’t render any UI
};

export default Web3Auth;
