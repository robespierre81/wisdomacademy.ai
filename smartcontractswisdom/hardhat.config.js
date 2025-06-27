require("@nomiclabs/hardhat-ethers");
require("dotenv").config();


module.exports = {
  networks: {
    wisdomAI: {
      url: process.env.BLOCKCHAIN_RPC, // RPC URL
      accounts: [process.env.PRIVATE_KEY], // Private Key from .env
      gas: 70000, // Set slightly below the block limit (to be safe)
      gasPrice: 10000 // Match the network's required gas price
    }
  },
  solidity: {
    version: "0.8.20", // ✅ Match the pragma
    settings: {
      optimizer: {
        enabled: true,
        runs: 200,
      },
    },
  },
  paths: {
    sources: "./contracts",
    artifacts: "./artifacts"
  }
};