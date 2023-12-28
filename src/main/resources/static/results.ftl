<#-- @ftlvariable name="events" type="kotlin.collections.List<com.example.models.GitEventModel>" -->
<#-- @ftlvariable name="name" type="String" -->
<#import "header.ftl" as layout>

<@layout.header>
    <h2>Logs for repo ${name}</h2>
<#list events as event>
<div>
        <b>${event.type}</b>:
        <#if (event.type == "PushEvent")>
        <div style="text-align: left; padding: 0 20% 0 20%">
            <p>Created at: ${event.created_at_str}</p>
            <p>Current head is: ${event.payload.before} &rarr; ${event.payload.head}</p>
            <#list event.payload.commits as commit>

                Author: ${commit.author.name} (${commit.author.email})
                <br> Message: ${commit.message}
            </#list>
        </div>
        </#if>
</div>
    <hr>
</#list>
<a href="/">Back to the main page</a>
</@layout.header>