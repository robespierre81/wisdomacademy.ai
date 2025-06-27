import { useEffect, useState } from 'react';
import '../../styles/Discussion.css';

export default function Discussion() {
  const [discussions, setDiscussions] = useState([]);
  const [newTitle, setNewTitle] = useState('');
  const [newContent, setNewContent] = useState('');
  const [newComment, setNewComment] = useState({});

  useEffect(() => {
    fetchDiscussions();
  }, []);

  const fetchDiscussions = async () => {
    const response = await fetch("https://wisdomacademy.ai/api/community/discussions", {
      method: "GET",
      mode: "cors",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      }
    });
    if (response.ok) {
      const data = await response.json();
      setDiscussions(data);
    }
  };

  const createDiscussion = async () => {
    await fetch("https://wisdomacademy.ai/api/community/discussions", {
      method: "POST",
      mode: "cors",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify({ title: newTitle, content: newContent })
    });
    setNewTitle('');
    setNewContent('');
    fetchDiscussions();
  };

  const addComment = async (discussionId) => {
    const comment = newComment[discussionId];
    if (!comment) return;
    await fetch(`https://wisdomacademy.ai/api/community/discussions/${discussionId}/comments`, {
      method: "POST",
      mode: "cors",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify({ content: comment })
    });
    setNewComment({ ...newComment, [discussionId]: '' });
    fetchDiscussions();
  };

  const vote = async (type, id, target) => {
    await fetch(`https://wisdomacademy.ai/api/community/${target}/${id}/vote`, {
      method: "POST",
      mode: "cors",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify({ type })
    });
    fetchDiscussions();
  };

  return (
    <div className="discussion-container">
      <h2 className="discussion-title">Discussions</h2>

      <div className="discussion-form">
        <input type="text" placeholder="Title" value={newTitle} onChange={e => setNewTitle(e.target.value)} />
        <textarea placeholder="Content" value={newContent} onChange={e => setNewContent(e.target.value)}></textarea>
        <button onClick={createDiscussion}>Post Discussion</button>
      </div>

      {discussions.map(d => (
        <div key={d.id} className="discussion-card">
          <h3 className="discussion-topic">{d.title}</h3>
          <p className="discussion-content">{d.content}</p>
          <small className="discussion-meta">
            By {d.author} on {new Date(d.createdAt).toLocaleString()}
          </small>
          <div className="discussion-votes">
            <button onClick={() => vote('up', d.id, 'discussions')}>👍 {d.upvotes}</button>
            <button onClick={() => vote('down', d.id, 'discussions')}>👎 {d.downvotes}</button>
          </div>

          <div className="discussion-comments">
            <h4>Comments</h4>
            {(d.comments || []).map(c => (
              <div key={c.id} className="comment">
                <p>{c.content}</p>
                <small>By {c.author} on {new Date(c.createdAt).toLocaleString()}</small>
                <div className="comment-votes">
                  <button onClick={() => vote('up', c.id, 'comments')}>👍 {c.upvotes}</button>
                  <button onClick={() => vote('down', c.id, 'comments')}>👎 {c.downvotes}</button>
                </div>
              </div>
            ))}
            <div className="comment-form">
              <textarea
                placeholder="Write a comment..."
                value={newComment[d.id] || ''}
                onChange={e => setNewComment({ ...newComment, [d.id]: e.target.value })}
              ></textarea>
              <button onClick={() => addComment(d.id)}>Post Comment</button>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}
