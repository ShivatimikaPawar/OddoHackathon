The Skill Swap Platform is a dynamic web application designed to connect individuals who want to exchange skills. Whether you're offering expertise in graphic design and seeking help with coding, or teaching guitar and looking to learn photography, this platform facilitates meaningful peer-to-peer learning through structured swaps.
Key Features
- User Profiles
  Each user has a customizable profile with location, availability, and a profile photo. Users can set their visibility and role (user or admin).
- Skill Management
  Users can list skills they offer and want, categorized for easy matching. Skills are stored and retrieved via a robust DAO layer.
- Skill Search
  Users can search for others based on specific skills, enabling targeted connections.
- Swap Requests
  Users can send, accept, reject, or cancel swap requests. Each request includes skill details, status tracking, and timestamps.
- Feedback System
  After a successful swap, users can submit ratings and comments to build trust and reputation.
- Admin Dashboard
  Admins can view all swap activity, ban users, and monitor platform health through a dedicated interface.

Technical Stack

Frontend- JSP, HTML, CSS 
Backend- Java Servlets, JDBC 
Database- MySQL 
Architecture- MVC (Model-View-Controller) 
Server- Apache Tomcat
IDE- Eclipse 

Design Highlights
- Modular DAO Layer for clean database access
- Servlet Controllers for handling business logic
- JSP Views with scriptlet-based rendering (no JSTL)
- Role-Based Access Control for user/admin separation
- Scalable Structure with packages for model, dao, controller, and jsp




