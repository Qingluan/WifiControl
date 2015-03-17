import os
import socket

class testSocket :

    def __init__(self,port,host=''):
        self.server = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        self.server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.server.bind((host,port))
        self.server.listen(200)
        print "server init .... ok"

    def getClient(self):
        while 1:
            client , clientAddr  = self.server.accept()

            print " connect from {}".format(clientAddr)

            buf =  client.recv(4096)
            print [i for i in buf ]
            check = 0x0000
            for ii in buf[2:-2]:
                check ^= ord(ii)
            print check , check % 8
            client.sendall("ok\n")
            client.close()


if  __name__ ==  "__main__" : 
    so = testSocket(8080)
    so.getClient()


