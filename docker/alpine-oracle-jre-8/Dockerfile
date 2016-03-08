# AlpineLinux with a glibc-2.21 and Oracle Java 8

FROM alpine:3.3

MAINTAINER SEVEN4N TEAM ACH

# Update Files
RUN apk update

# Install bash
RUN apk add bash

# Install cURL
RUN apk --update add curl ca-certificates tar && \
    curl -# -Ls https://circle-artifacts.com/gh/andyshinn/alpine-pkg-glibc/6/artifacts/0/home/ubuntu/alpine-pkg-glibc/packages/x86_64/glibc-2.21-r2.apk > /tmp/glibc-2.21-r2.apk && \
    apk add --allow-untrusted /tmp/glibc-2.21-r2.apk && \
    rm /tmp/glibc-2.21-r2.apk

# Add help to find what's the container ip
RUN echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf

# Java Version
ENV JAVA_VERSION_MAJOR 8
ENV JAVA_VERSION_MINOR 66
ENV JAVA_VERSION_BUILD 17
ENV JAVA_PACKAGE       jre

# Download and unarchive Java
RUN mkdir /opt && curl -# -jksSLH "Cookie: oraclelicense=accept-securebackup-cookie"\
  http://download.oracle.com/otn-pub/java/jdk/${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-b${JAVA_VERSION_BUILD}/${JAVA_PACKAGE}-${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-linux-x64.tar.gz \
    | tar -xzf - -C /opt &&\
    ln -s /opt/jre1.${JAVA_VERSION_MAJOR}.0_${JAVA_VERSION_MINOR} /opt/jre &&\
    rm -rf /opt/jre/lib/plugin.jar \
           /opt/jre/lib/ext/jfxrt.jar \
           /opt/jre/bin/javaws \
           /opt/jre/lib/javaws.jar \
           /opt/jre/lib/desktop \
           /opt/jre/plugin \
           /opt/jre/lib/deploy* \
           /opt/jre/lib/*javafx* \
           /opt/jre/lib/*jfx* \
           /opt/jre/lib/amd64/libdecora_sse.so \
           /opt/jre/lib/amd64/libprism_*.so \
           /opt/jre/lib/amd64/libfxplugins.so \
           /opt/jre/lib/amd64/libglass.so \
           /opt/jre/lib/amd64/libgstreamer-lite.so \
           /opt/jre/lib/amd64/libjavafx*.so \
           /opt/jre/lib/amd64/libjfx*.so

# Ajuste para que funcione Jenkins
RUN ln -s /lib/libc.musl-x86_64.so.1 /usr/lib/libc.musl-x86_64.so.1

# Set environment
ENV JAVA_HOME /opt/jre
ENV PATH ${PATH}:${JAVA_HOME}/bin

