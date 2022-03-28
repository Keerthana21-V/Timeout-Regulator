import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Typography from '@material-ui/core/Typography';
//import Skeleton from '@material-ui/lab/Skeleton';
import Divider from '@material-ui/core/Divider';

import getStatus from "./getStatus"

const useStyles = makeStyles(theme => ({
  root: {
    width: '100%',
    backgroundColor: theme.palette.background.paper,
  },
  inline: {
    display: 'inline',
  },
  block: {
    display: 'block',
  },
  itemMargin : {
    marginLeft : "20px",
  },
  dot : {
    height: "15px",
    width: "15px",
    borderRadius: "50%",
    display: "block",
    float : "right",
    marginTop : "8px",
  }
}));



export default function LeaveRequestItem(props) {
  const classes = useStyles();

  return (
      <div>
      <ListItem alignItems="flex-start" 
      onClick={props.handleClick} 
      applicationBody={props.applicationBody}
      responseBody={props.responseBody}
      responseStatus = {props.status}
      >
      <ListItemText
        disableTypography = {true}
        secondary={
          <React.Fragment>
            <Typography component="span" variant="body2" className={classes.inline} 
            color="textPrimary">
              {props.name}
            </Typography>
            <Typography component="span" variant="body2" 
            className={[classes.inline,classes.itemMargin]} color="textPrimary">
              {"Leave Type : "+props.typeOfLeave}
            </Typography>
            <Typography component="span" variant="body2" 
            className={[classes.inline,classes.itemMargin]} color="textSecondary">
              {"Duration : "+props.leaveStart}
            </Typography>
             {" - "}
            <Typography component="span" variant="body2" className={classes.inline} 
            color="textSecondary">
              {props.leaveEnd}
            </Typography>
          </React.Fragment>
        }
      />
      <span className={classes.dot} 
      style={getStatus(props.status)}>
      </span>
    </ListItem>
    <Divider variant="middle" component="li" />
    </div>
  );
}