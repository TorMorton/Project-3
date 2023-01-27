<h1>Warehouse Inventory Project</h1>
<h2>Angular/Spring Boot/MySQL</h2>

<p>This project is a simple inventory management system for a warehouse. It is a full-stack application that uses Angular for the front-end, Spring Boot for the back-end, and MySQL for the database.</p>

<h4>Main Page Requirements</h4>
<ul>
    <li>List company name on page</li>
    <li>Section containing all of the company's warehouses</li>
</ul>

<h4>Warehouse Page</h4>
<ul>
    <li>Displays warehouse capacity</li>
    <li>Displays all of the current items</li>
    <li>Add feature</li>
    <li>Delete feature</li>
    <li>Edit feature</li>
    <li>Contains link to home page</li>
</ul>

<h4>Extra Credit</h4>
<ul>
    <li>Add statistics to webpage</li>
    <li>Assign users and roles (Spring Security)</li>
    <li>Add internationalization support (i.e. i18n or ngq-translate</li>
    <li>Deploy using AWS</li>
</ul>

<h4>To connect to MySQL in Spring Boot:</h4>
<ul>
    <li>
        Create a folder named resources /src/main/
    </li>
    <li>
        Create an application.properties with the following properties:
    </li>
    <ol>
        <li>
            spring.datasource.url=jdbc:mysql://localhost:3306/cpu_warehouse
        </li>
        <li>
            spring.datasource.username=root
        </li>
        <li>
            spring.datasource.password= (your pwd goes here)
        </li>
        <li>
            spring.jpa.hibernate.ddl-auto=update
        </li>
        <li>
            spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
        </li>
    </ol>
</ul>


