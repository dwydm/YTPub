import React from 'react'
import whytipi from '../layout/whytipi.png';

export default function Welcome() {
  return (
    <div class='welcome-guest'>

<       div className='topics-logo'>
            <img src={whytipi}></img>
        </div>
        <div>
            <br></br>
            <p>Welcome to WHYTIPI</p>

            <div class=" text-wrap">
            <p class="lh-1">YTPublisher helps you with creation and topic management</p>
            <p class="lh-1">gathering sources for your next aplication, and also</p>
            <p class="lh-1">coordinating your work with other editors.</p></div>
        </div>


    </div>
  )
}
