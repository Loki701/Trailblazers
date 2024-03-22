import React from 'react';

const Community = () => {
  const styles = {
    communityPage: {
      fontFamily: 'Arial, sans-serif',
      padding: '20px',
      backgroundColor: '#f9f9f9',
    },
    title: {
      color: '#333',
      textAlign: 'center',
    },
    intro: {
      marginBottom: '20px',
    },
    section: {
      backgroundColor: '#fff',
      borderRadius: '5px',
      padding: '20px',
      marginBottom: '20px',
    },
    sectionTitle: {
      color: '#444',
    },
    sectionContent: {
      color: '#666',
    },
  };

  return (
    <div style={styles.communityPage}>
      <h1 style={styles.title}>Welcome to Our Community Page</h1>
      <p style={styles.intro}>This is a place where our community members can connect and share.</p>

      <div style={styles.section}>
        <h2 style={styles.sectionTitle}>Upcoming Events</h2>
        <p style={styles.sectionContent}>Event 1: Date and Time</p>
        <p style={styles.sectionContent}>Event 2: Date and Time</p>
      </div>

      <div style={styles.section}>
        <h2 style={styles.sectionTitle}>Community Guidelines</h2>
        <p style={styles.sectionContent}>Please be respectful and considerate of others. This is a place for constructive discussions and sharing of ideas.</p>
      </div>

      <div style={styles.section}>
        <h2 style={styles.sectionTitle}>Latest Posts</h2>
        <p style={styles.sectionContent}>Post 1: Brief description</p>
        <p style={styles.sectionContent}>Post 2: Brief description</p>
      </div>

      <div style={styles.section}>
        <h2 style={styles.sectionTitle}>Join the Community</h2>
        <p style={styles.sectionContent}>Interested in joining our community? Click here to sign up!</p>
      </div>
    </div>
  );
};

export default Community;