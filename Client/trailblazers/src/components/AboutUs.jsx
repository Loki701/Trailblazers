import React from "react";
// import '@fortawesome/fontawesome-free/css/all.css';


const AboutUs = () => {
    let message="For the final semester of our undergraduate studies at the University of Florida, the Trailblazers team plans to deliver an educational web application for shortest path algorithms. The website will enable users to select a pathfinding algorithm, and witness an exciting animation that demonstrates how the algorithm works as well as how well it works. Additionally, the website will feature informational text that describes in words how each algorithm functions. The objective of the Trailblazers team is to make learning about algorithms more fun, engaging, and accessible for computer science students. ";
    
    return (
        <section className="section-white">
            <div className="container">
                <div className="content">
                    <h2 className="col-md- text-center">
                        Who We Are
                    </h2>
                    <p className="message">{message}</p>
                    <h3 className="developers">Our Team</h3>
                </div>
                <div className="team-row">
                        <div className="team-item">
                            <img src="daniel.jpg" className="team-img" alt="danielPic"/>
                            <h3>DANIEL SERRANO</h3>
                            <div className="team-infor">
                                <p>Back-End Developer</p>

                                <ul className="icons">
                                   <li> <a href="#" className="github">
                                        <i className="fa-brands fa-facebook"></i>
                                        </a></li>
                                   <li> <a href="#" className="linkedin">
                                        <i className="fa-brands fa-linkedin"></i>
                                        
                                        </a></li>
                                    <li> <a href="#" className="facebook">
                                        <i className="fa-brands fa-github"></i>
                                        </a></li>
                                </ul>
                            </div>
                        </div>
                    
                        <div className="team-item">
                            <img src="josef.jpg" className="team-img" alt="josePic"
                                
                            />
                            <h3>JOSE FIGUEREDO</h3>
                            <div className="team-infor">
                                <p>Front-End/Back-End Developer</p>

                                <ul className="icons">
                                   <li> <a href="#" className="github">
                                        <i className="fa-brands fa-facebook"></i>
                                        </a></li>
                                   <li> <a href="#" className="linkedin">
                                        <i className="fa-brands fa-linkedin"></i>
                                        
                                        </a></li>
                                    <li> <a href="#" className="facebook">
                                        <i className="fa-brands fa-github"></i>
                                        </a></li>
                                </ul>
                            </div>
                        </div>

                        <div className="team-item">
                            <img src="axel.jpg" className="team-img" alt="axelPic"/>
                            <h3>AXEL AQUINO</h3>
                            <div className="team-infor">
                                <p>Front-End Developer</p>

                                <ul className="icons">
                                   <li> <a href="#" className="github">
                                        <i className="fa-brands fa-facebook"></i>
                                        </a></li>
                                   <li> <a href="#" className="linkedin">
                                        <i className="fa-brands fa-linkedin"></i>
                                        
                                        </a></li>
                                    <li> <a href="#" className="facebook">
                                        <i className="fa-brands fa-github"></i>
                                        </a></li>
                                </ul>
                            </div>
                        </div>
                        <div className="team-item">
                            <img src="eldhose.jpg" className="team-img" alt="eldhosePic"/>
                            <h3>ELDHOSE SALBY</h3>
                            <div className="team-infor">
                                <p>Front-End Developer</p>

                                <ul className="icons">
                                   <li> <a href="#" className="github">
                                        <i className="fa-brands fa-facebook"></i>
                                        </a></li>
                                   <li> <a href="#" className="linkedin">
                                        <i className="fa-brands fa-linkedin"></i>
                                        
                                        </a></li>
                                    <li> <a href="#" className="facebook">
                                        <i className="fa-brands fa-github"></i>
                                        </a></li>
                                </ul>
                            </div>
                        </div>
                
                        <div className="team-item">
                            <img src="jonathan.jpg" className="team-img" alt="jonathanPic"/>
                            <h3>JONATHAN RODRIGUES</h3>
                            <div className="team-infor">
                                <p>Back-End Developer</p>

                                <ul className="icons">
                                   <li> <a href="#" className="github">
                                        <i className="fa-brands fa-facebook"></i>
                                        </a></li>
                                   <li> <a href="#" className="linkedin">
                                        <i className="fa-brands fa-linkedin"></i>
                                        </a></li>
                                    <li> <a href="#" className="facebook">
                                        <i className="fa-brands fa-github"></i>
                                        </a></li>
                                </ul>
                            </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default AboutUs;
