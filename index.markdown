---
layout: default
title: Danger
---

# Danger Concerns and Decisions

None right now, but there usually are some before I get around to creating a
release.

Codes are cheaper than new classes and do about what new classes do.

Ideally, you can use existing exception objects.

Probably the best thing to happen to `Exception` is `String.format`. 

## Use the exceptions you have, even if you hate them

Because I hate checked exceptions, but in the case of `PomReader` in Cups Maven,
it made more sense to throw `FileNotFoundException` instead of creating a single
exception called `PomException` to report the only exception that it raises.

## Don't Know, Don't Care

After giving a lot of thought to exception handling, I finally came to a
strategy that I call, Don't Know, Don't Care. This is the strategy that I employ
when someone has used a checked exception, lest Gostling's Ghost haunts them
until the end of time. These are fear of commitment exceptions, based on the
fear of commitment patterns, expose only interfaces, and throw the buck. The
author imagined that in the future, the software would be running on cyborg
hamsters, and could not anticipate all of the eventualities of hamster
malfunction, so they created an interface that throws the buck.

These APIs are not designed and not considered. They are irresponsible APIs.
They push the responsibility onto the developers with a notion of the world is
just, you get what you deserve, you shouldn't be programming if you can't handle
any exception that my crappy library throws at you. These are petulant little
libraries and on top of all that, they take themselves too seriously.

The old XML librares are particularly petulant, so here's and example of Don't
Know, Don't Care.

    TransformerHandler handler;
    try {
        handler = ((SAXTransformerFactory) TransformerFactory.newInstance()).newTransformerHandler();
    } catch (TransformerConfigurationException e) {
        throw new RuntimeException(e);
    }

All I've done here is ask for a default instance of a transformer and built it
with no settings. If an exception is raised, it is in now way the responsiblity
of these six lines of code. There is nothing that can be done in the program.
Maybe something has happened along the lines of a full disk, or maybe the
installation of the JRE is damaged, or maybe, or maybe, or maybe, but the
checked exception here can only be wrapped and rethrown.

Why bother to rethrow it as anything but a `RuntimeException`, wrapped,
without a message? 

## Recovery

The nonsense of recovery. There are so few exceptions that are meant to be
recoverable.

## Danger

Now that I'm thinking about it, the notion of a stack trace with extra
diagnostic information, that is not to terribly useful. I can imagine a case
where you might want to add a list of things that went wrong, or some such, but
it seems to me, that instead of adding to the given exception, simply build a
new exception to nest the one that has been thrown.

Use an existing exception where you can, but the problem is that the existing
message may lack an important meaning. (Where is that example where you are
taking an action based on a flag?).

If you have a framework, where something down the line is pluggable, then you
are going to need to use your own exception. Cups, or Mix, those are examples of
heavy messaging, while something like infuse, see if you cannot come up with a
set of error codes, wrapping.

But, the notion of a crash report. That is probably too heavy for any of the
libraries that I've created. Plus, the more exception branches you have, the
harder it is to test things. Exceptions should not be thrown, they should be
exceptional. Adding a lot of exception handling logic adds a lot of logic that
is difficult to test.

## But, I Already Tested That

Another painful part...

Testing that a file can be read, or testing the access modifier of the method...

## No Exceptions

One of the biggest boondoggles is reflection. That has a really peculiar
exception ladder, it can raise all sorts of different exceptions, but you'll
have to ask yourself, which of these exceptions can you reasonably handle?

In the case of diffuse, I decided that the API was not pluggable per say. We're
recording data. If the data cannot be serialized, then that is a programming
error, and a rather raw one at that. There was only one possible exception
thrown by diffuse, and that was the inability to get a getter. We'd already
tested for access. So, an exception is going to be an ugly one. 

Unlike a complicated system, we're talking about something that would mostly
likely be an exception thrown from within the getter. This is going to be
exceedingly rare. The library has made a point that it only wants to work with
POJOs, and that getters should not have side effects. Thus, this exception is
the result of someone who just isn't there yet. They are probably going to show
up in the listserv asking some rudiementary questsions, or maybe they are trying
to satisfy too many fetishes.

Which is why for Diffuse, I've decided to get rid of the DiffuseException and
raise an `IllegalArgumentException` which most correctly describes what has
happened here. It is a way of pushing the blame back onto the user, but why in
the world does the getter of the bean you gave me throw an exception?

Here we can decide to let `SecurityException` propagate, and we know that
`IllegalArgumentException` is never triggered because we never called.

## Infuse

With infuse, how can you recover? The data is corrupt. However, you could be
using in infuse in a why where you're trying to parse something, but if it is
not valid, you'll do something else. You can recover from a little bit of bad
data, and infuse is a little bit of bad data.

## String Beans

Is a lot of bad data. String beans is not meant to store a bajillion.

## The Data Map

Really became a bad idea, because what is the point? Why would an exception
contain data? For diagnostics, I suppose. Put that in the message, perhaps, but
why would you put it in a data map, where people can inspect it? Because, it if
it is data that is relevant to the caller, it is probably on the call stack. If
you can't open a file the caller gives, then you don't have to throw the file
back at them, just let them know it couldn't open.

## Paste

There are places where the developer may have done something really wrong, like
annotating a private method as `Output` when only public methods are allowed.
Once could simply skip the method, not find an `Output`, but I'd like to raise
an exception when the reflection raises an exception, what what sort?

There is a new problem, because in Diffuse you could argue that you were given
an illegal argument, but here it is a misplaced annotation. It is not an illegal
state, since I think of that as the state of memory and such at runtime.

Thus, I could create an exception, and call it `IllegalAnnotationException`, but
that means I need to document the exception, which in turn means I'm supposed to
talk about the exception.

So, oddly, I'm going to wrap these exceptions that are not meant to be caght in
`RuntimeException` and note that Java's checked exception fetish forsaw a world
where all software was robust and recoverable in process, while the reality is
that software is so much more robust if it can simply restart gracefully, and it
is a mode of operation that administrators can understand.

Ultimately, I decided that the chances that a caught `RuntimeException` was not
directly releated to reflection were so slim, that it was silly to jump through
hoops to sort out the exception. Ultimately, I decided that reflection is simply
not the special case that everyone makes it out to be. The use of reflection is
common, and the use cases are easily identifable, as are the abuses.

And then, so long as the message is the truth, I'm merely adding context to the
stack trace, which might as well be there. I don't need to state that I am
expecting that the error is related to reflection, merely that what I intended
to accomplish has failed. It is a is I called a method
`doSomethingReallyComplicated` and passed it a file, and I wanted to wrap the
caught exception in order to note that I called the method with that file.


## Danger

Invocation target exception. As far as the others go, it is really no different
than deploying an application with a few JARs missing. Same unrecoverable
errors, but with a focal point. The only reason we fuss so much about reflection
exceptions is those darned checked exceptions seemed so valiant at one time.

Really buys me little to have `LibraryException` and have that be for any error
the `com.acme.library` might throw.

## Addendum

Addendum used CodedDanger, as did Dovetail but none of the conditions coded are
remotely recoverable. Something that does not parse, does not parse, for
Dovetail, and then for Addendum, where is there a place to catch the exception?
It is all innocent facing.

## Jav-a-Go-Go

Also, coded, by then, isn't that the perfect candidate for this?
