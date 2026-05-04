# Stage 1: Build
FROM registry.access.redhat.com/ubi8/nodejs-18:latest AS builder
USER root
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve
FROM registry.access.redhat.com/ubi8/nginx-120:latest
USER root

# Clean out the default directory to be absolutely sure
RUN rm -rf /opt/app-root/src/*

# Copy your built files to the folder Nginx is actually using
COPY --from=builder /app/dist /opt/app-root/src

# OPENSHIFT SPECIAL: Grant permissions to the Root Group (0)
# This is required because OpenShift uses random UIDs at runtime
RUN chgrp -R 0 /opt/app-root/src && \
    chmod -R g=u /opt/app-root/src

# Switch back to the standard non-root user
USER 1001
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]