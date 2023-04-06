import React from 'react'

export default function AlertError({ message }) {
  return (
    <div class="alert alert-danger" role="alert">
    ERROR: {message}
    </div>
  )
}
