const Web3 = require('web3'); // Correctly require Web3

// Create a new Web3 instance with the specified blockchain URL
const web3 = new Web3("http://82.165.73.209:8545");

// Test the connection by getting the latest block number
web3.eth.getBlockNumber()
  .then((blockNumber) => {
    console.log('Latest Block Number:', blockNumber);
  })
  .catch((error) => {
    console.error('Error connecting to the blockchain:', error);
  });