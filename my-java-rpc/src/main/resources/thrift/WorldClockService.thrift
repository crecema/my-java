namespace java com.crecema.my.java.rpc.thrift

struct Request {
    1: string zoneId
}

struct Response {
    1: string localDateTime
}

service WorldClockService {
    Response getLocalDateTime(1: Request request)
}