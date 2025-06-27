import { useEffect, useState } from 'react';
import axios from 'axios';
import '../../styles/Events.css';

export default function Events() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    axios.get('/api/community/events')
      .then(res => setEvents(res.data));
  }, []);

  return (
    <div className="events-container">
      <h2 className="events-title">Events & Webinars</h2>
      <div className="events-list">
        {events.map(event => (
          <div key={event.id} className="event-card">
            <h3 className="event-title">{event.title}</h3>
            <p className="event-description">{event.description}</p>
            <p className="event-meta">
              Speaker: {event.speaker} <br />
              Start: {new Date(event.startTime).toLocaleString()} <br />
              End: {new Date(event.endTime).toLocaleString()}
            </p>
            <a href={event.registrationLink} target="_blank" rel="noreferrer" className="event-link">
              Register
            </a>
          </div>
        ))}
      </div>
    </div>
  );
}
