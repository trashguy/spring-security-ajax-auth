# Spring Security auth with ajax or form post

This is an example on how to authenticate with spring security 
sans the annoying templates

I spent more time than I would like to admit trying to figure out how 
to do this. Most examples for spring security rely on some template 
engine with a form login. For what I wanted, I just needed a simple endpoint
for an ajax call to be made and then setup a session. This is an example of the
simplest way I found to achieve this.

From here I can implement a DaoAuthenticationProvider or similar to 
authenticate against another microservice.

I need to spend some more time and add the ability to post a JSON object
for auth.
