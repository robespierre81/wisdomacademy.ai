import { useEffect, useState } from 'react';
import axios from 'axios';
import '../../styles/Badges.css';

export default function Badges() {
  const [badges, setBadges] = useState([]);

  useEffect(() => {
    axios.get('/api/community/badges')
      .then(res => setBadges(res.data));
  }, []);

  return (
    <div className="badges-container">
      <h2 className="badges-title">Badges</h2>
      <div className="badges-grid">
        {badges.map(badge => (
          <div key={badge.id} className="badge-card">
            <img src={badge.iconUrl} alt={badge.name} className="badge-icon" />
            <h3 className="badge-name">{badge.name}</h3>
            <p className="badge-description">{badge.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
