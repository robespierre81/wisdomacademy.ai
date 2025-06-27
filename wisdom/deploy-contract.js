const Web3 = require('web3');

// Connect to your blockchain
const web3 = new Web3("http://74.208.195.193:8545");

// Account to deploy the contract
const account = "0x5ae47b3d0206fa42386189cf33bc21E3938A0386"; // Replace with your account
const password = "";        // Replace with your account's password

// Unlock account
async function unlockAccount() {
    const unlocked = await web3.eth.personal.unlockAccount(account, password, 600);
    console.log("Account unlocked:", unlocked);
}

// ERC20 Contract Bytecode and ABI
const bytecode = "0x..."; // Replace with the bytecode of your compiled contract
const abi = [ ... ];      // Replace with the ABI of your compiled contract

// Deploy the contract
async function deployContract() {
    const contract = new web3.eth.Contract(abi);

    const deployOptions = {
        data: bytecode,
        arguments: [] // Constructor arguments, if any
    };

    const gasEstimate = await web3.eth.estimateGas({
        from: account,
        data: contract.deploy(deployOptions).encodeABI()
    });

    const newContractInstance = await contract.deploy(deployOptions)
        .send({
            from: account,
            gas: gasEstimate
        });

    console.log("Contract deployed at address:", newContractInstance.options.address);
}

(async () => {
    await unlockAccount();
    await deployContract();
})();
