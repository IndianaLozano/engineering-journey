## 2026-11-25
- Set up two GitHub accounts on one Mac: an SSH host alias per account and a per-folder git identity (includeIf).
- Why it works: SSH picks the key by Host name; git picks the identity by folder.
- Image vs container, in my words:  An image is a read-only template: your app, its dependencies and its configuration, built in layers. A container is a running instance of that image, with its own thin writable layer on top. It's like a class and an object: from one image you can run many containers.
