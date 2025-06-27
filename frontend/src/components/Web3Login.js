import React, { useState, useEffect } from 'react';
import { Web3Provider } from '@ethersproject/providers';

const Web3Login = ({ onConnect }) => {
  const [account, setAccount] = useState(null);
  const [error, setError] = useState(null);

  const connectWallet = async () => {
  try {
    if (window.ethereum) {
      const provider = new Web3Provider(window.ethereum);
      await provider.send("eth_requestAccounts", []);
      const signer = provider.getSigner();
      const address = await signer.getAddress();
      setAccount(address);
      onConnect && onConnect(address);
    } else {
      setError('MetaMask not detected');
    }
  } catch (err) {
    setError('Failed to connect wallet');
    console.error(err);
  }
};

  // Automatically attempt to connect wallet on component mount
  useEffect(() => {
    connectWallet();
  }, []);

  return (
    <div>
    </div>
  );
};

export default Web3Login;
