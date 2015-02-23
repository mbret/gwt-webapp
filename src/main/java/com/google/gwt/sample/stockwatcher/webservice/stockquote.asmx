<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://www.webserviceX.NET/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://www.webserviceX.NET/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://www.webserviceX.NET/">
      <s:element name="GetQuote">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="symbol" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetQuoteResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetQuoteResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="string" nillable="true" type="s:string" />
    </s:schema>
  </wsdl:types>
  <wsdl:message name="GetQuoteSoapIn">
    <wsdl:part name="parameters" element="tns:GetQuote" />
  </wsdl:message>
  <wsdl:message name="GetQuoteSoapOut">
    <wsdl:part name="parameters" element="tns:GetQuoteResponse" />
  </wsdl:message>
  <wsdl:message name="GetQuoteHttpGetIn">
    <wsdl:part name="symbol" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetQuoteHttpGetOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:message name="GetQuoteHttpPostIn">
    <wsdl:part name="symbol" type="s:string" />
  </wsdl:message>
  <wsdl:message name="GetQuoteHttpPostOut">
    <wsdl:part name="Body" element="tns:string" />
  </wsdl:message>
  <wsdl:portType name="StockQuoteSoap">
    <wsdl:operation name="GetQuote">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Get Stock quote for a company Symbol</wsdl:documentation>
      <wsdl:input message="tns:GetQuoteSoapIn" />
      <wsdl:output message="tns:GetQuoteSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="StockQuoteHttpGet">
    <wsdl:operation name="GetQuote">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Get Stock quote for a company Symbol</wsdl:documentation>
      <wsdl:input message="tns:GetQuoteHttpGetIn" />
      <wsdl:output message="tns:GetQuoteHttpGetOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:portType name="StockQuoteHttpPost">
    <wsdl:operation name="GetQuote">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Get Stock quote for a company Symbol</wsdl:documentation>
      <wsdl:input message="tns:GetQuoteHttpPostIn" />
      <wsdl:output message="tns:GetQuoteHttpPostOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="StockQuoteSoap" type="tns:StockQuoteSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="GetQuote">
      <soap:operation soapAction="http://www.webserviceX.NET/GetQuote" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="StockQuoteSoap12" type="tns:StockQuoteSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="GetQuote">
      <soap12:operation soapAction="http://www.webserviceX.NET/GetQuote" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="StockQuoteHttpGet" type="tns:StockQuoteHttpGet">
    <http:binding verb="GET" />
    <wsdl:operation name="GetQuote">
      <http:operation location="/GetQuote" />
      <wsdl:input>
        <http:urlEncoded />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="StockQuoteHttpPost" type="tns:StockQuoteHttpPost">
    <http:binding verb="POST" />
    <wsdl:operation name="GetQuote">
      <http:operation location="/GetQuote" />
      <wsdl:input>
        <mime:content type="application/x-www-form-urlencoded" />
      </wsdl:input>
      <wsdl:output>
        <mime:mimeXml part="Body" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="StockQuote">
    <wsdl:port name="StockQuoteSoap" binding="tns:StockQuoteSoap">
      <soap:address location="http://www.webservicex.net/stockquote.asmx" />
    </wsdl:port>
    <wsdl:port name="StockQuoteSoap12" binding="tns:StockQuoteSoap12">
      <soap12:address location="http://www.webservicex.net/stockquote.asmx" />
    </wsdl:port>
    <wsdl:port name="StockQuoteHttpGet" binding="tns:StockQuoteHttpGet">
      <http:address location="http://www.webservicex.net/stockquote.asmx" />
    </wsdl:port>
    <wsdl:port name="StockQuoteHttpPost" binding="tns:StockQuoteHttpPost">
      <http:address location="http://www.webservicex.net/stockquote.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>