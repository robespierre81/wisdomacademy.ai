FROM node:20 AS build

ENV HTTPS=true
ENV SSL_CRT_FILE=/app/ssl/cert.pem
ENV SSL_KEY_FILE=/app/ssl/key.pem

WORKDIR /app
COPY package*.json ./
COPY . .

RUN npm install
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
# Copy custom Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 8000
CMD ["nginx", "-g", "daemon off;"]

