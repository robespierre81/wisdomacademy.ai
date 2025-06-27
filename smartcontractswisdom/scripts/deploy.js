const hre = require("hardhat");

async function main() {
    const [deployer] = await hre.ethers.getSigners();

    console.log("Deploying contract with the account:", deployer.address);

    const TokenDistributor = await hre.ethers.getContractFactory("TokenDistributor");

    const contract = await TokenDistributor.deploy({
        gasLimit: 7000000,
        maxFeePerGas: hre.ethers.utils.parseUnits("5", "gwei"),
        maxPriorityFeePerGas: hre.ethers.utils.parseUnits("1", "gwei")
    });

    await contract.deployed();
    console.log("Contract deployed at:", contract.address);
}

main().catch((error) => {
    console.error(error);
    process.exitCode = 1;
});
