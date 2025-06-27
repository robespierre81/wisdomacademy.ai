// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

/// @title WISDOM Token (WSD) - ERC20 for Wisdom Academy
contract InitialToken is ERC20 {

    /// @notice Deploys the WISDOM token and mints `initialSupply` to the deployer
    /// @param initialSupply The amount to mint, in whole tokens (will be multiplied by 10 ** decimals)
    constructor(uint256 initialSupply) ERC20("WISDOM", "WSD") {
        _mint(msg.sender, initialSupply * (10 ** decimals()));
    }
}
