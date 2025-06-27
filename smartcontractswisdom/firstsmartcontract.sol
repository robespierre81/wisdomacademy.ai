// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract InitialToken is ERC20 {
    constructor(uint256 initialSupply) ERC20("WISDOM", "WSD") {
        _mint(msg.sender, initialSupply * (0.1 ** decimals()));
    }
}
