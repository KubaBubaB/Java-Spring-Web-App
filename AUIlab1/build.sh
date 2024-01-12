#!/usr/bin/env bash
# Builds all applications and all docker images using Dockerfile and tags it based on org.opencontainers.image.version
# label in Dockerfile.
#######################################
# Script main function. Builds all applications and all docker images using Dockerfile and tags it based on
# org.opencontainers.image.version label in Dockerfile.
# Arguments:
#   None.
#######################################
function main() {
    cd ./auilab4person/; sh ./build.sh; cd ..
    cd ./auilab4job/; sh ./build.sh; cd ..
    cd ./auigateway/; sh ./build.sh; cd ..
    cd ./auiangular/; sh ./build.sh; cd ..
    docker compose up -d
}
main "$@"