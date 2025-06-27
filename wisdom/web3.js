
const Web3 = require("web3");
const web3 = new Web3("http://74.208.195.193:8545");

const contractAddress = "0xDeployedContractAddress";
const contract = new Web3.eth.Contract(abi, contractAddress);

const mintTokens = async () => {
  const account = "0x5ae47b3d0206fa42386189cf33bc21E3938A0386";

  await contract.methods
    .mint(account, web3.utils.toWei("1000", "ether")) // Mint 1000 tokens
    .send({ from: account, gas: 300000 });

  console.log("Tokens minted to:", account);
};

mintTokens();
