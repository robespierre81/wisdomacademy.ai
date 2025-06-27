import batteryIcon from '../pictures/battery.png';
import carIcon from '../pictures/car.png';
import financialIcon from '../pictures/financial.png';
import sportsIcon from '../pictures/sports.png';

const NavigationPane = ({ isLoggedIn, onToolSelect }) => {
  if (!isLoggedIn) {
    return <p>Please log in to access the tools.</p>;
  }

  return (
    <div id="navigationPane">
      <div className="icon" onClick={() => onToolSelect('electricity')}>
        <img src={batteryIcon} alt="Electricity" /><br />
        Electricity
      </div>
      <div className="icon" onClick={() => onToolSelect('car')}>
        <img src={carIcon} alt="Car" /><br />
        Car
      </div>
      <div className="icon" onClick={() => onToolSelect('financial')}>
        <img src={financialIcon} alt="Financial" /><br />
        Financial
      </div>
      <div className="icon" onClick={() => onToolSelect('sports')}>
        <img src={sportsIcon} alt="Sports" /><br />
        Sports
      </div>
    </div>
  );
};

export default NavigationPane;
