import React, { useState } from 'react';
import PullUps from './Sports/PullUps';
import PushUps from './Sports/PushUps';
import LongBarBells from './Sports/LongBarBells';
import ShortBarBells from './Sports/ShortBarBells';
import Cycling from './Sports/Cycling';
import Rowing from './Sports/Rowing';
import Squats from './Sports/Squats';
import SitUps from './Sports/SitUps';
import Steps from './Sports/Steps';
import Weight from './Sports/Weight';
import Blood from './Sports/Blood';
import Food from './Sports/Food';
import SportsOverview from './Sports/Overview';

const Sports = () => {
  const [selectedOperation, setSelectedOperation] = useState(null);

  // Function to render the selected component
  const renderSelectedOperation = () => {
    switch (selectedOperation) {
      case 'blood':
        return <Blood />;
      case 'pullups':
        return <PullUps />;
      case 'pushups':
        return <PushUps />;
      case 'long':
        return <LongBarBells />;
      case 'short':
        return <ShortBarBells />;
      case 'cycling':
        return <Cycling />;
      case 'rowing':
        return <Rowing />;
      case 'squats':
        return <Squats />;
      case 'situps':
        return <SitUps />;
      case 'steps':
        return <Steps />;
      case 'weight':
        return <Weight />;
      case 'food':
        return <Food />;
      default:
        return <SportsOverview />;
    }
  };

  return (
    <div>
      <h2>Sports</h2>
      <table>
        <tbody>
          <tr>
            <th onClick={() => setSelectedOperation('pullups')} id="pullups">Pull-Ups</th>
            <th onClick={() => setSelectedOperation('pushups')} id="pushups">Push-Ups</th>
            <th onClick={() => setSelectedOperation('long')} id="long">Long Barbell</th>
            <th onClick={() => setSelectedOperation('short')} id="short">Short Barbell</th>
            <th onClick={() => setSelectedOperation('cycling')} id="cycling">Cycling</th>
            <th onClick={() => setSelectedOperation('rowing')} id="rowing">Rowing</th>
            <th onClick={() => setSelectedOperation('squats')} id="squats">Squats</th>
            <th onClick={() => setSelectedOperation('situps')} id="situps">Sit-Ups</th>
            <th onClick={() => setSelectedOperation('steps')} id="steps">Steps</th>
            <th onClick={() => setSelectedOperation('weight')} id="weight">Weight</th>
            <th onClick={() => setSelectedOperation('blood')} id="blood">Blood</th>
            <th onClick={() => setSelectedOperation('food')} id="food">Food</th>
          </tr>
        </tbody>
      </table>

      <div id="contentcontent" style={{ overflow: 'auto' }}>
        {renderSelectedOperation()}
      </div>
    </div>
  );
};

export default Sports;
