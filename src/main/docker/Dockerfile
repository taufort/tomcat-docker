FROM centos:7.3.1611
MAINTAINER taufort

#
# UTF-8 by default
#
ENV LANG=en_US.UTF-8 \
    LANGUAGE=en_US:en \
    LC_ALL=en_US.UTF-8

# Setting timezone
RUN rm /etc/localtime &&\
    ln -s /usr/share/zoneinfo/Europe/Paris /etc/localtime

# Adding JDK and Tomcat to the path
ENV JAVA_HOME /opt/jdk
ENV CATALINA_HOME /opt/tomcat
ENV PATH ${JAVA_HOME}/bin:${CATALINA_HOME}/bin:$PATH

# Install Oracle Java 8
ENV JAVA_VERSION 8u111
ENV JAVA_PATH_VERSION 1.8.0_111
ENV JAVA_BUILD 8u111-b14
ENV JAVA_MIRROR http://download.oracle.com/otn-pub/java/jdk

# Downloading, extracting, cleaning of JDK and creating a symbolic link to /opt/jdk
# https://gist.github.com/HennTakipi/e4ffb051b0a1ea246c61#file-docker-java-txt
RUN curl -jksSLH "Cookie: oraclelicense=accept-securebackup-cookie" \
  ${JAVA_MIRROR}/${JAVA_BUILD}/jdk-${JAVA_VERSION}-linux-x64.tar.gz \
    | tar -xzf - -C /opt && \
    ln -s /opt/jdk${JAVA_PATH_VERSION} /opt/jdk && \
    rm -rf /opt/jdk/*src.zip \
           /opt/jdk/lib/missioncontrol \
           /opt/jdk/lib/visualvm \
           /opt/jdk/lib/*javafx* \
           /opt/jdk/jre/lib/plugin.jar \
           /opt/jdk/jre/lib/ext/jfxrt.jar \
           /opt/jdk/jre/bin/javaws \
           /opt/jdk/jre/lib/javaws.jar \
           /opt/jdk/jre/lib/desktop \
           /opt/jdk/jre/plugin \
           /opt/jdk/jre/lib/deploy* \
           /opt/jdk/jre/lib/*javafx* \
           /opt/jdk/jre/lib/*jfx* \
           /opt/jdk/jre/lib/amd64/libdecora_sse.so \
           /opt/jdk/jre/lib/amd64/libprism_*.so \
           /opt/jdk/jre/lib/amd64/libfxplugins.so \
           /opt/jdk/jre/lib/amd64/libglass.so \
           /opt/jdk/jre/lib/amd64/libgstreamer-lite.so \
           /opt/jdk/jre/lib/amd64/libjavafx*.so \
           /opt/jdk/jre/lib/amd64/libjfx*.so \
    && rm -rf /tmp/*

# Install Tomcat
ENV TOMCAT_MAJOR 8
ENV TOMCAT_VERSION 8.5.13
ENV TOMCAT_MIRROR http://apache.mirrors.ovh.net/ftp.apache.org/dist/tomcat

# Downloading, extracting and creating a symbolic link to /opt/tomcat
RUN curl -sS ${TOMCAT_MIRROR}/tomcat-${TOMCAT_MAJOR}/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    | tar -xzf - -C /opt && \
    ln -s /opt/apache-tomcat-${TOMCAT_VERSION} ${CATALINA_HOME}

# Adding execution rights to tomcat shell scripts
RUN chmod +x ${CATALINA_HOME}/bin/*sh

# Adding war to the docker image
ADD ${tomcat.final-artifact-name}.war ${CATALINA_HOME}/webapps/tomcatDocker.war

EXPOSE 8080
EXPOSE 8009

CMD ["/opt/tomcat/bin/catalina.sh", "run"]