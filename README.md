<h2>YTPublisher</h2>
<p>YTPublisher is a project designed for news-based YouTube channels. It facilitates the organization of sources, links, and fosters collaboration between authors. Additionally, it allows users to assign statuses to topics, enabling the tracking of their life cycle on social media.</p>

<h3>Installation and requirements</h3>
<p>YTPub contains both client and the server</p>
<p>For npm and Node.js installation, follow documentation below<br>https://docs.npmjs.com/downloading-and-installing-node-js-and-npm</p>
<p>To run client side, go to <b>src/main/frontend/ytpclient</b> and type <b>npm start</b>.
By default client is running on port http://localhost:3000.</p>

<p>Please note that any changes to the port would require you to make further changes in the rest controllers
located in <b>/ytpub/web/rest</b> package. Simply change annotation "@CrossOrigin("http://localhost:3000")" to the port you desire</p>

