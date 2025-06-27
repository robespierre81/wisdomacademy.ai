  async function connectBlockchain() {
      if (window.ethereum) {
          try {
              // Request account access
              await window.ethereum.request({ method: "eth_requestAccounts" });
              
              // Create Web3 instance
              const web3 = new Web3(window.ethereum);
              
              // Get accounts
              const accounts = await web3.eth.getAccounts();
              console.log("Connected account:", accounts[0]);

              // Check balance
              const balance = await web3.eth.getBalance(accounts[0]);
              console.log("Balance:", web3.utils.fromWei(balance, "ether"), "ETH");

          } catch (error) {
              console.error("User denied account access:", error);
          }
      } else {
          console.error("MetaMask not detected. Please install it.");
      }
  }
  
    const contractABI = [ /* Add your ABI here */ ];
    const contractAddress = "0xYourContractAddress";

    async function interactWithContract() {
        const web3 = new Web3(window.ethereum);
        const contract = new web3.eth.Contract(contractABI, contractAddress);
        const accounts = await web3.eth.getAccounts();

        // Example: Calling a function from the smart contract
        const result = await contract.methods.someFunction().call({ from: accounts[0] });
        console.log("Contract Response:", result);
    }


  // Connect on page load
  window.addEventListener("load", connectBlockchain);


