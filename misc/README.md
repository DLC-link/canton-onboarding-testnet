# Onboarding general information

## Prerequisite

Please check whether you have everything set up properly [here](../README.md).

## Access the remote node

To successfully run the provided scripts, the remote service port must be accessible from your local machine. This means the remote port needs to be forwarded or exposed to your localhost.

### Why is this necessary?

Without exposing or forwarding this port to your local environment, the scripts will fail to connect and will not work properly.

### Kubernetes-native environment

Assume you have set up the kubectl connection to the cluster where the participant nodes are running. Identify the pods with:

```bash
kubectl get pods | grep participant
```

This will print out the pod running the participant, so you can port-forward:

```bash
kubectl port-forward "{participant_pod_name}" 5002:5002
```

So the port is not exposed to your local environment.

#### Port-forward script

You can find an established script for that [here](./scripts/kubectl-expose.sh).

- This script assumes you have only one participant node in the cluster.
- After this, you will probably need to open another terminal.
- You can stop the port-forward by pressing Ctrl+C.

### Docker-native

If you are running the service inside a Docker container, you can expose the port by starting the container with the port mapping option:

```bash
docker run -p 5002:5002 ...
```