// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract TokenDistributor {
    mapping(address => uint256) private balances;
    mapping(address => bool) private hasReceivedTokens;

    uint256 public constant INITIAL_TOKENS = 1000;

    event TokensDistributed(address indexed user, uint256 amount);

    function claimTokens() public {
        require(!hasReceivedTokens[msg.sender], "You have already received your tokens.");
        
        balances[msg.sender] = INITIAL_TOKENS;
        hasReceivedTokens[msg.sender] = true;

        emit TokensDistributed(msg.sender, INITIAL_TOKENS);
    }

    function getBalance(address user) public view returns (uint256) {
        return balances[user];
    }
}
