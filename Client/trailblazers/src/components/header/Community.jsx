import React, { useEffect, useState } from 'react';

const Community = () => {
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        setLoaded(true);
    }, []);
  const styles = {
    communityPage: {
      fontFamily: 'Arial, sans-serif',
      padding: '20px',
      backgroundColor: '#000', // Changed to black
      color: '#fff', // Changed to white
      opacity: loaded ? 1 : 0,
            transition: 'opacity 1s ease-in-out',
            animationName: 'fadeIn',
            animationDuration: '2s'
    },
    title: {
      color: '#fff', // Changed to white
      textAlign: 'center',
    },
    intro: {
      marginBottom: '20px',
    },
    section: {
      backgroundColor: '#000', // Changed to black
      borderRadius: '5px',
      padding: '20px',
      marginBottom: '20px',
    },
    sectionTitle: {
      color: '#fff', // Changed to white
    },
    sectionContent: {
      color: '#fff', // Changed to white
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