// Sources flattened with hardhat v2.22.18 https://hardhat.org

// SPDX-License-Identifier: MIT

// File @openzeppelin/contracts/token/ERC20/IERC20.sol@v5.2.0

// Original license: SPDX_License_Identifier: MIT
// OpenZeppelin Contracts (last updated v5.1.0) (token/ERC20/IERC20.sol)

pragma solidity ^0.8.20;

/**
 * @dev Interface of the ERC-20 standard as defined in the ERC.
 */
interface IERC20 {
    /**
     * @dev Emitted when `value` tokens are moved from one account (`from`) to
     * another (`to`).
     *
     * Note that `value` may be zero.
     */
    event Transfer(address indexed from, address indexed to, uint256 value);

    /**
     * @dev Emitted when the allowance of a `spender` for an `owner` is set by
     * a call to {approve}. `value` is the new allowance.
     */
    event Approval(address indexed owner, address indexed spender, uint256 value);

    /**
     * @dev Returns the value of tokens in existence.
     */
    function totalSupply() external view returns (uint256);

    /**
     * @dev Returns the value of tokens owned by `account`.
     */
    function balanceOf(address account) external view returns (uint256);

    /**
     * @dev Moves a `value` amount of tokens from the caller's account to `to`.
     *
     * Returns a boolean value indicating whether the operation succeeded.
     *
     * Emits a {Transfer} event.
     */
    function transfer(address to, uint256 value) external returns (bool);

    /**
     * @dev Returns the remaining number of tokens that `spender` will be
     * allowed to spend on behalf of `owner` through {transferFrom}. This is
     * zero by default.
     *
     * This value changes when {approve} or {transferFrom} are called.
     */
    function allowance(address owner, address spender) external view returns (uint256);

    /**
     * @dev Sets a `value` amount of tokens as the allowance of `spender` over the
     * caller's tokens.
     *
     * Returns a boolean value indicating whether the operation succeeded.
     *
     * IMPORTANT: Beware that changing an allowance with this method brings the risk
     * that someone may use both the old and the new allowance by unfortunate
     * transaction ordering. One possible solution to mitigate this race
     * condition is to first reduce the spender's allowance to 0 and set the
     * desired value afterwards:
     * https://github.com/ethereum/EIPs/issues/20#issuecomment-263524729
     *
     * Emits an {Approval} event.
     */
    function approve(address spender, uint256 value) external returns (bool);

    /**
     * @dev Moves a `value` amount of tokens from `from` to `to` using the
     * allowance mechanism. `value` is then deducted from the caller's
     * allowance.
     *
     * Returns a boolean value indicating whether the operation succeeded.
     *
     * Emits a {Transfer} event.
     */
    function transferFrom(address from, address to, uint256 value) external returns (bool);
}


// File contracts/CourseManager.sol

// Original license: SPDX_License_Identifier: MIT
pragma solidity ^0.8.0;

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
