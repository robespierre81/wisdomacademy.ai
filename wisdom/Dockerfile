# Stage 1: Build the custom geth binary
FROM golang:1.22-alpine as builder

# Install required tools
RUN apk add --no-cache build-base linux-headers git make

# Set the working directory for building
WORKDIR /build

# Copy Go module files
COPY ./wisdombc/go.mod ./wisdombc/go.sum  ./

# Download Go dependencies
RUN go mod download

# Copy the blockchain source files
COPY ./wisdombc /build

# Build the geth binary
RUN go build -o /bin/geth ./cmd/geth

# Stage 2: Minimal image to run the blockchain
FROM alpine:latest

# Install runtime dependencies
RUN apk add --no-cache ca-certificates

# Set the working directory
WORKDIR /app

# Copy the compiled geth binary from the builder stage
COPY --from=builder /bin/geth /usr/local/bin/geth

# Copy the blockchain files (including genesis.json) into the container
COPY ./wisdombc /app
RUN ls -l /app

# Initialize the blockchain using genesis.json
RUN mkdir -p /data && geth --datadir /data init /app/genesis.json

# Expose Ethereum ports
EXPOSE 8545 30303 30303/udp

# Start the Ethereum node with dynamic bootnodes and miner address
ENTRYPOINT ["geth"]

CMD ["geth", "--datadir", "/data", "--networkid", "2307", "--http", "--http.addr", "0.0.0.0", "--http.port", "8545", "--http.api", "eth,net,web3,personal,miner,clique", "--allow-insecure-unlock", "--http.corsdomain", "http://e-softworks.consulting,http://127.0.0.1,http://localhost", "--unlock", "0x5ae47b3d0206fa42386189cf33bc21E3938A0386", "--password", "/app/password.txt", "--mine", "--miner.etherbase", "0x5ae47b3d0206fa42386189cf33bc21E3938A0386"]
