// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract CentralWallet {
    address public owner;

    mapping(address => uint256) public balances;

    event Deposited(address indexed sender, uint256 amount);
    event Withdrawn(address indexed recipient, uint256 amount);

    modifier onlyOwner() {
        require(msg.sender == owner, "Only owner can withdraw funds");
        _;
    }

    constructor() {
        owner = msg.sender;
    }

    function deposit() public payable {
        balances[msg.sender] += msg.value;
        emit Deposited(msg.sender, msg.value);
    }

    function withdraw(address payable recipient, uint256 amount) public onlyOwner {
        require(address(this).balance >= amount, "Insufficient funds in contract");
        recipient.transfer(amount);
        emit Withdrawn(recipient, amount);
    }

    function getBalance() public view returns (uint256) {
        return address(this).balance;
    }
}
