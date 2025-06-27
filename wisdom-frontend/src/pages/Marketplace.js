import React from "react";
import { Link } from "react-router-dom";
import "../styles/Marketplace.css";

const Marketplace = () => {
    return (
        <div className="marketplace">
            <h1>Wisdom Academy Marketplace</h1>
            <p>Manage your tokens, rewards, and transactions.</p>

            <div className="marketplace-links">
                <Link to="/marketplace/buy-tokens" className="marketplace-card">💰 Buy Tokens</Link>
                <Link to="/marketplace/earn-rewards" className="marketplace-card">🎁 Earn Rewards</Link>
                <Link to="/marketplace/my-transactions" className="marketplace-card">📜 My Transactions</Link>
            </div>
        </div>
    );
};

export default Marketplace;