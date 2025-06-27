const contractAddress = "0x5ae47b3d0206fa42386189cf33bc21E3938A0386"; // Replace with the deployed contract address

async function mintTokens(to, amount) {
    const contract = new web3.eth.Contract(abi, contractAddress);

    await contract.methods.mint(to, web3.utils.toWei(amount.toString(), 'ether')).send({
        from: account,
        gas: 200000
    });

    console.log(`${amount} tokens minted to ${to}`);
}

(async () => {
    await mintTokens("0xRecipientAddress", 1000); // Replace with recipient address and token amount
})();
