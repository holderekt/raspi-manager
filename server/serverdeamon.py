#!/usr/bin/env python3

import socket, sys, json, os, base64

class Data:
    def __init__(self, string):
        parsed_json = json.loads(string)
        self.name = parsed_json["username"]
        self.password = parsed_json["password"]
        self.value = parsed_json["value"]


class SocketConnection:
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    users = json.load(open(os.path.join(sys.path[0], "whitelist.json")))['users']
    blacklist = json.load(open(os.path.join(sys.path[0], "blacklist.json")))['commands']

    def __init__(self, address='', port=8080):
        self.server_address = (address, port)
        self.sock.bind(self.server_address)
        self.sock.listen(5)

    def run(self):
        while True:
            connection, _ = self.sock.accept()
            string = base64.b64decode(self.getData(connection).encode('ascii')).decode('ascii')
            print(string)
            datareceived = Data(string)

            if(self.checkUser(datareceived)):
                self.executeCommand(datareceived.value)
        
            connection.close()


    def checkUser(self, data):
        for user in self.users:
            if(data.name == user["name"]):
                if(data.password == user["password"]):
                    return True
                else:
                    return False
        return False
    

    def executeCommand(self, command):
        if(command == "shutdown"):
            os.system("shutdown 0")
        elif(command == "reboot"):
            os.system("reboot 0")
        else:
            for word in self.blacklist:
                if word in command:
                    return -1
            os.system(command)
        return 0


    def getData(self, connection):
        returnvalue = ""
        while True:
            data = connection.recv(1024)
            returnvalue = returnvalue + data.decode("utf-8")
            if not data:
                break
        return returnvalue


connection = SocketConnection()
connection.run()
