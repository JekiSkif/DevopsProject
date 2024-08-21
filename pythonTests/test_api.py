import requests
import pytest
import logging

#configure base URL of our devops server
BASE_URL = "http://localhost:8080/jobs"

#logger config
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)


def log_response(response) -> None:
    """
    Utility functions to log the HTTP response's status code and it's body
    :param response: an HTTP response object
    :return: None
    """
    try:
        response_body = response.json()
    except ValueError:
        response_body = "No JSON Response"
    logger.info(f"Status Code: {response.status_code}")
    logger.info(f"Response Body: {response_body}")


@pytest.fixture(scope='module')
def setup():
    print("\n Setup for the module")
    yield
    print("\n Teardown for the module")


@pytest.fixture(scope="module")
def test_job():
    return {
        "jobName": "Test Job",
        "status": "NEW",
        "jobType": "BUILD",
        "createdAt": "2023-07-27T00:00:00",
        "updatedAt": "2023-07-27T00:00:00"
    }

@pytest.fixture(scope="module")
def base_url():
    """
    Fixture to provide the base URL for the API.
    :return: Base URL for API requests
    """
    return BASE_URL

def test_get_all_jobs(base_url):
    response = requests.get(f"{base_url}/jobs")
    log_response(response)
    assert response.status_code == 200
    assert isinstance(response.json(), list)


def test_create_job(base_url, test_job):
    response = requests.post(f"{base_url}/jobs", json=test_job)
    log_response(response)
    assert response.status_code == 200
    assert response.json()["jobName"] == test_job["jobName"]


def test_get_job_by_id(base_url, test_job):
    # Create a job first to ensure it exists
    create_response = requests.post(f"{base_url}/jobs", json=test_job)
    job_id = create_response.json()["id"]

    response = requests.get(f"{base_url}/jobs/{job_id}")
    log_response(response)
    assert response.status_code == 200
    assert response.json()["jobName"] == test_job["jobName"]


def test_update_job_status(base_url, test_job):
    # Create a job first to ensure it exists
    create_response = requests.post(f"{base_url}/jobs", json=test_job)
    job_id = create_response.json()["id"]

    updated_status = {"status": "UPDATED"}
    response = requests.put(f"{base_url}/jobs/{job_id}", json=updated_status)
    log_response(response)
    assert response.status_code == 200
    assert response.json()["status"] == "UPDATED"


def test_delete_job(base_url, test_job):
    # Create a job first to ensure it exists
    create_response = requests.post(f"{base_url}/jobs", json=test_job)
    job_id = create_response.json()["id"]

    response = requests.delete(f"{base_url}/jobs/{job_id}")
    log_response(response)
    assert response.status_code == 200

    # Ensure the job is deleted
    get_response = requests.get(f"{base_url}/jobs/{job_id}")
    log_response(get_response)
    assert get_response.status_code == 404
