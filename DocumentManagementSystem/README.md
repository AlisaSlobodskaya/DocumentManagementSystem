<h1>Document management system</h1>
This program is a client-server system for secure document management between company employees.
The company's employees have their own hierarchy:
<ul>
<li>Director</li>
<li>Head of Department</li>
<li>Department Specialist</li>
</ul>
Depending on their position, everyone has certain opportunities.
The main meaning of this system is the distribution of instructions, binding documents to them and their signing (confirmation).
<h2>Docker</h2>
Run postgresql database
<br><code>
docker run <br>
-e POSTGRES_HOST_AUTH_METHOD=trust <br>
-e POSTGRES_DB=YOUR_URL <br>
-e POSTGRES_USER=YOUR_USER <br>
-e POSTGRES_PASSWORD=YOUR_PASS <br>
--rm -p 5432:5432 -d postgres 
</code></br>
<br> Create image and run application
<br><code>
docker build --tag document_management_system . <br>
docker run -p 8080:8080 document_management_system
</code></br>

<h2>Endpoints</h2>
<h4>Admin</h4>
<ul>
<li><code>GET</code>/admin/users - get all users</li>
<li><code>GET</code>/admin/users/{id} - get user by id</li>
<li><code>GET</code>/admin/users/{id}/delete - delete user by id form</li>
<li><code>GET</code>/admin/users/{id}/update - update user by id form</li>
<li><code>GET</code>/admin/users/new - save new user form</li>
</ul>