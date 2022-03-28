export default function getStatus(status){

    const statusIndicator = {
        approved : {backgroundColor:"#3D3"},
        rejected : {backgroundColor:"#D33"},
        notProcessed : {backgroundColor:"#FB3"},
        revoked : {backgroundColor:"#888"}
    }

    switch(status){
      case "NOT_PROCESSED" : return statusIndicator.notProcessed;
      case "ACCEPTED" :
      case "EXTENDED" : return statusIndicator.approved;
      case "REJECTED" : return statusIndicator.rejected;
      case "REVOKED"  : return statusIndicator.revoked;
    }
}