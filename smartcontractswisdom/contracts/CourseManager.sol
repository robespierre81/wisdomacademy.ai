// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/IERC20.sol";

contract CourseManager {
    IERC20 public wsdToken;
    address public owner;

    event CoursePurchased(address indexed user, uint256 indexed courseId, uint256 price);

    mapping(uint256 => uint256) public coursePrices;
    mapping(address => mapping(uint256 => bool)) public hasAccess;

    constructor(address _wsdTokenAddress) {
        wsdToken = IERC20(_wsdTokenAddress);
        owner = msg.sender;
    }

    function setCoursePrice(uint256 courseId, uint256 price) external {
        require(msg.sender == owner, "Only owner can set prices");
        coursePrices[courseId] = price;
    }

    function buyCourse(uint256 courseId) external {
        uint256 price = coursePrices[courseId];
        require(price > 0, "Course not available");

        // Transfer tokens from buyer to contract
        require(wsdToken.transferFrom(msg.sender, address(this), price), "Payment failed");

        hasAccess[msg.sender][courseId] = true;
        emit CoursePurchased(msg.sender, courseId, price);
    }

    function hasBought(address user, uint256 courseId) public view returns (bool) {
        return hasAccess[user][courseId];
    }
}
