<#import "header.ftl" as layout/>
<@layout.header>
<p>This tool allows you to retrieve logs of a GitHub repository.
    <br>Usage: enter the name of the user and the repository.
</p>
<form method="post">
    <input placeholder="Username" name="user" type="text">
    <input placeholder="Repository" name="repo" type="text">
    <input placeholder="Number of elements" name="num" type="number">
    <input type="submit" value="Submit">
</form>
</@layout.header>
