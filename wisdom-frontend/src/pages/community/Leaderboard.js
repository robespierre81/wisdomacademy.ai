import { useEffect, useState } from 'react';
import axios from 'axios';
import '../../styles/Leaderboard.css';

export default function Leaderboard() {
  const [entries, setEntries] = useState([]);

  useEffect(() => {
    axios.get('/api/community/leaderboard')
      .then(res => setEntries(res.data));
  }, []);

  return (
    <div className="leaderboard-container">
      <h2 className="leaderboard-title">Leaderboard</h2>
      <table className="leaderboard-table">
        <thead>
          <tr>
            <th>Rank</th>
            <th>User</th>
            <th>Points</th>
          </tr>
        </thead>
        <tbody>
          {entries.sort((a, b) => a.rank - b.rank).map(entry => (
            <tr key={entry.id}>
              <td>{entry.rank}</td>
              <td>{entry.username}</td>
              <td>{entry.points}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
